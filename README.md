fluent-logger-mock-sender
=============

Synopsis
---

```java
Properties props = System.getProperties();
props.setProperty(Config.FLUENT_SENDER_CLASS, MockSender.class.getName());

FluentLogger logger = FluentLogger.getLogger("tag-prefix");

Map<String, Object> map1 = new HashMap<>();
map1.put("foo", "bar");
Map<String, Object> map2 = new HashMap<>();
map2.put("buz", "qux");

logger.log("app", map1, 1430294276); // Don't send to fluentd. Stack to list
logger.log("db", map2, 1430294277); // Ditto

if (logger.getSender() instanceof MockSender) {
    MockSender sender = (MockSender) logger.getSender();
    sender.getFluentLogs(); // <= Returns List<FluentLog> which is stacked by FluentLogger#log()
    sender.clearFluentLogs(); // <= Clears stacked
}
```

Description
--

Provides a mock sender for fluent logger.

This mock doesn't send anything to fluentd, it stacks logs to internal list instead.
It means you can pick up a log from list as you like. This mock sender makes easy for testing and debugging.

Author
--

moznion (<moznion@gmail.com>)

License
--

```
The MIT License (MIT)
Copyright © 2015 moznion, http://moznion.net/ <moznion@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the “Software”), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```

