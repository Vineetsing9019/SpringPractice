# Daily push: copies not-yet-pushed Spring Boot classes into SpringPractice and pushes each with a learning note.
# Run by the Windows scheduled task "SpringPractice daily push". -Count sets how many classes (default 4),
# -DryRun tests without committing, -Force pushes again on a day that already has a push.
param([int]$Count = 4, [switch]$DryRun, [switch]$Force)

$ErrorActionPreference = 'Stop'
$Repo    = 'D:\SpringPractice'
$Log     = Join-Path $Repo 'tools\daily-push.log'
$Pushed  = Join-Path $Repo 'daily\pushed.txt'
$JavaPushed = 'D:\Practice\daily\pushed.txt'   # files JavaPractice already has are not pushed again
$Source  = 'C:\Users\Vineet\Downloads\Spring_Projects'
$Skip    = @('\target\', '\PetHaven\', '\.mvn\', '\bin\')
$Secrets = @(Get-Content 'D:\daily-push\secrets.txt' -ErrorAction SilentlyContinue | Where-Object { $_ })   # passwords to hide, one per line; the file is outside every repo
$BS      = [string][char]92

# Project folder -> topic it teaches.
$Topics = @{
    'basics'             = 'Spring Boot basics'
    'BookManagement'     = 'Spring Data JPA'
    'OrmDemo'            = 'ORM with Hibernate and JPA'
    'PetAdoption'        = 'a Spring Boot REST project'
    'ProductCrud'        = 'a Spring Boot CRUD REST API'
    'ProductCrudd'       = 'a Spring Boot CRUD REST API'
    'Relationship'       = 'JPA entity relationships'
    'SecurityDemo'       = 'Spring Security'
    'ShopKart'           = 'a Spring Boot e-commerce project'
    'UserValidation'     = 'bean validation in Spring'
    'UserSpringDataRestDemoUserValidation-1' = 'Spring Data REST with validation'
}
$Layers = @{
    'controller'   = 'REST controllers'; 'controllers' = 'REST controllers'
    'models'       = 'entity models';    'model'       = 'entity models'; 'entity' = 'entity models'; 'entities' = 'entity models'
    'repositories' = 'repositories';     'repository'  = 'repositories'
    'services'     = 'the service layer'; 'service'    = 'the service layer'
    'dto'          = 'DTOs';             'config'      = 'configuration'
    'exception'    = 'exception handling'; 'exceptions' = 'exception handling'
}

# A file is pushed only if it has real code: after comments, package and imports, the class body is not
# empty (or the type extends / implements something, e.g. a Spring Data repository).
function Has-Code($path) {
    $t = Get-Content $path -Raw -ErrorAction SilentlyContinue
    if (-not $t) { return $false }
    $t = [regex]::Replace($t, '(?s)/\*.*?\*/', '')
    $t = [regex]::Replace($t, '(?m)//.*$', '')
    $t = [regex]::Replace($t, '(?m)^\s*(package|import)\s+[^;]+;', '')
    $open = $t.IndexOf('{'); $close = $t.LastIndexOf('}')
    if ($open -lt 0 -or $close -le $open) { return $false }
    $body = $t.Substring($open + 1, $close - $open - 1).Trim()
    return [bool]($body -or $t.Substring(0, $open) -match '\b(extends|implements)\b')
}

function Log($msg) {
    $line = "{0:yyyy-MM-dd HH:mm:ss}  {1}" -f (Get-Date), $msg
    Add-Content -Path $Log -Value $line -Encoding utf8
    Write-Host $line
}

function Get-Topic($rel) {
    $parts   = $rel.Split($BS)
    $project = $parts[0]
    $parent  = $parts[$parts.Length - 2]
    $topic   = $Topics[$project]
    if (-not $topic) { $topic = ($project -creplace '([a-z])([A-Z])', '$1 $2').ToLower() }
    if ($rel -like "*${BS}src${BS}test${BS}*") { return "testing $topic" }
    $layer = $Layers[$parent]
    if ($layer) { $topic = "$layer in $topic" }
    return $topic
}

try {
    Set-Location $Repo
    # Pull only when GitHub already has the branch (the first push creates it).
    if (-not $DryRun -and (git ls-remote --heads origin main)) { git pull --rebase --quiet origin main }
    $today = Get-Date -Format 'yyyy-MM-dd'
    if (-not (Test-Path $Pushed)) { New-Item -ItemType File -Path $Pushed -Force | Out-Null }
    $done = @(Get-Content $Pushed -ErrorAction SilentlyContinue) + @(Get-Content $JavaPushed -ErrorAction SilentlyContinue)

    if ((Get-Content $Pushed | Where-Object { $_ -like "$today|*" }) -and -not $DryRun -and -not $Force) {
        Log "Already pushed today; nothing to do."
        exit 0
    }

    $candidates = @(Get-ChildItem -Path $Source -Recurse -Filter *.java -File | Where-Object {
        $p = $_.FullName
        -not ($Skip | Where-Object { $p -like "*$_*" }) -and
        -not ($done | Where-Object { $_.Split('|')[1] -eq $p }) -and
        (Has-Code $p)
    })
    if (-not $candidates) { Log "No unpushed Spring classes left."; exit 0 }

    foreach ($pick in ($candidates | Get-Random -Count ([Math]::Min($Count, $candidates.Count)))) {
        $rel   = $pick.FullName.Substring($Source.Length).TrimStart($BS)
        $topic = Get-Topic $rel
        $dest  = Join-Path $Repo ("daily" + $BS + $today + $BS + $rel)
        if ($DryRun) { Log "Dry run would push $($pick.FullName)  [$topic]"; continue }
        New-Item -ItemType Directory -Path (Split-Path $dest) -Force | Out-Null

        $content = Get-Content $pick.FullName -Raw
        foreach ($sec in $Secrets) { $content = $content.Replace($sec, '<password>') }
        $header = "// Learning $topic - practice class from $($rel.Split($BS)[0]), pushed $today`r`n"
        Set-Content -Path $dest -Value ($header + $content) -Encoding utf8
        Log "Picked $($pick.FullName) -> $dest  [$topic]"

        $relDest = "daily/$today/" + $rel.Replace($BS, '/')
        Add-Content -Path (Join-Path $Repo 'README.md') -Value ("| $today | [{0}]({1}) | Learning {2} |" -f $pick.Name, $relDest, $topic) -Encoding utf8
        Add-Content -Path $Pushed -Value "$today|$($pick.FullName)" -Encoding utf8

        git add -A
        git commit --quiet -m "Learning $topic`: $($pick.Name)" -m "Practice class from $rel."
        git push --quiet -u origin main
        Log "Pushed $($pick.Name)"
    }
}
catch {
    Log "FAILED: $($_.Exception.Message)"
    exit 1
}
