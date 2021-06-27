$url = "https://chromedriver.storage.googleapis.com/83.0.4103.39/chromedriver_win32.zip"
$output = "$PSScriptRoot\chromedriver_win32.zip"
$start_time = Get-Date

$FileName = "$PSScriptRoot\chromedriver.exe"
if (Test-Path $FileName) 
{
  Remove-Item $FileName
}


$wc = New-Object System.Net.WebClient
$wc.DownloadFile($url, $output)
Write-Output "Time taken: $((Get-Date).Subtract($start_time).Seconds) second(s)"o
Expand-Archive -LiteralPath chromedriver_win32.zip
rm chromedriver_win32.zip
mv chromedriver_win32/chromedriver.exe ./
$DriveFolder="$PSScriptRoot\chromedriver_win32"
Remove-Item $DriveFolder -Recurse -ErrorAction Ignore