# cli

jvm的第一个部分，读取命令行输入。

## usage

```shell
alva_jvm main_class [options] [args...]
```

## options

|短选项|长选项|参数列表|描述|
|---|---|---|---|
|-cp|--classpath|&lt;path&gt;|to specify class search path of directories and zip/jar files|
|-h|--help|no args|to print the help message|

## 实现方式

使用第三方库`org.apache.commons.cli`。

第一步，构建`options`。

```java
Cli.options = new Options();
Option classpath = Option.builder("cp")
                         .longOpt("classpath")
                         .hasArg(true)
                         .argName("path")
                         .numberOfArgs(1)
                         .desc("to specify class search path of directories and zip/jar files")
                         .build();
Option help = Option.builder("h")
                    .longOpt("help")
                    .hasArg(false)
                    .desc("to print the help message")
                       .build();
Cli.options.addOption(classpath).addOption(help);
```

> 啥？代码块上边写个`java`就真的要连什么class都要写？笑话呐？

第二步，解析命令行。

```java
CommandLineParser parser = new DefaultParser();
this.commandLine = parser.parse(Cli.options, args);
```

第三步，查询，也就是执行。

```java
if (this.commandLine.hasOption("h")) {
    Cli.help();
    return;
}
if (this.commandLine.getArgs().length == 0) throw new ParseException("Main class required");
//Todo: execute instructions
```

Todo是后续要写的内容，接口还没写好。

## 灌水

这个东西...

```shell
alva_jvm [options] main_class [args...]
```

说实话，`alva_jvm`压根就没有用到...代码只会监听`[options] main_class [args...]`部分。

`alva_jvm`应该是命令。这要是c++直接编译就可以了，但是这是java。

目前看来，想要实现这个命令，要么是写个alias，要么就是编译成exe可执行文件。

alias的话，正常思路不是把整个项目打包成带依赖的jar，然后用java命令运行这个jar嘛，所以把前面用到的命令设个别名就行啦。

就大概是，powershell的话，去`$profile`对应的文件里边写个函数：

```shell
function alva_jvm {
    java -jar <jar_path>
}
```

shell的话，直接别名应该就可以了？没试过。

编译成exe还没试，细枝末节啦细枝末节。