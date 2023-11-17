
$clases = " ./src/*.java ./src/Operations/*.java"
$compile = "javac -d ./bin/ " + "$clases"
$CreateJarFile = "jar -cfm miExcel.jar Manifesto.txt -C ./bin/ ."
$RunCommand = "$compile" + " && " + "$CreateJarFile"

Invoke-Expression $RunCommand
