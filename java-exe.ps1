$clases = " ./src/*.java ./src/Operations/*.java"
$compile = "javac -d ./bin/ " + "$clases"
$CreateJarFile = "jar -cfm miExcel.jar Manifesto.txt -C ./bin/ ."
$javaCommand = "java -jar miExcel.jar"
$RunCommand = "$compile" + " && " + "$CreateJarFile" + " && " + "$javaCommand"

Invoke-Expression $RunCommand
