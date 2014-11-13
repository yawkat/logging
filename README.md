logging
=======

Logging utilities (not implementations).

java.util.logging
-----------------

```
Logger logger = Logger.getLogger("test");
Handler handler = new ConsoleHandler();
handler.setFormatter(FormatterBuilder.createDefault().build());
// alternatively just FormatterBuilder.createDefault().build(handler);
Loggers.replaceHandlers(logger, handler);
```