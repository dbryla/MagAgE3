import static ch.qos.logback.core.spi.FilterReply.ACCEPT
import static ch.qos.logback.core.spi.FilterReply.DENY

appender("STDOUT", ConsoleAppender) {
  filter(ch.qos.logback.classic.filter.LevelFilter) {
  	level = INFO
  	onMatch = DENY
  	onMismatch = ACCEPT
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%-5level %logger{30} - %msg%n"
  }
}

appender("LISTENER", FileAppender) {
  file = "log/listeners.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

appender("ERROR", FileAppender) {
  filter(ch.qos.logback.classic.filter.ThresholdFilter) {
    level = ERROR
  }
  file = "log/error.log"
  append = false
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %logger{30} - %msg%n"
  }
}

appender("SERVER", FileAppender) {
  file = "log/server.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

appender("THYMELEAF", FileAppender) {
  file = "log/thymeleaf.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

appender("HAZELCAST", FileAppender) {
  file = "log/hazelcast.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

appender("SPRING", FileAppender) {
  file = "log/spring.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

appender("AGE", FileAppender) {
  file = "log/age.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}


root(ALL, ["STDOUT"])
logger("org.thymeleaf", ALL, ["THYMELEAF", "ERROR"], false)
logger("com.hazelcast", ALL, ["HAZELCAST", "ERROR"], false)
logger("org.age", ALL, ["AGE", "ERROR"], false)
logger("org.springframework", ALL, ["SPRING", "ERROR"], false)
logger("org.age.mag", ALL, ["SERVER", "ERROR"])
logger("org.age.mag.hazelcast.listeners", ALL, ["LISTENER", "ERROR"], false)

