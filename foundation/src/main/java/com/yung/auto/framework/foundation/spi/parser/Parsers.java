package com.yung.auto.framework.foundation.spi.parser;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class Parsers {

    public Parsers() {
    }

    public static Parsers.DateParser forDate() {
        return Parsers.DateParser.INSTANCE;
    }

    public static Parsers.DurationParser forDuration() {
        return Parsers.DurationParser.INSTANCE;
    }

    public static enum DurationParser {
        INSTANCE;

        private static final Pattern PATTERN = Pattern.compile("(?:([0-9]+)D)?(?:([0-9]+)H)?(?:([0-9]+)M)?(?:([0-9]+)S)?(?:([0-9]+)(?:MS)?)?", 2);
        private static final int HOURS_PER_DAY = 24;
        private static final int MINUTES_PER_HOUR = 60;
        private static final int SECONDS_PER_MINUTE = 60;
        private static final int MILLIS_PER_SECOND = 1000;
        private static final int MILLIS_PER_MINUTE = 60000;
        private static final int MILLIS_PER_HOUR = 3600000;
        private static final int MILLIS_PER_DAY = 86400000;

        private DurationParser() {
        }

        public long parseToMillis(String text) throws ParserException {
            Matcher matcher = PATTERN.matcher(text);
            if (matcher.matches()) {
                String dayMatch = matcher.group(1);
                String hourMatch = matcher.group(2);
                String minuteMatch = matcher.group(3);
                String secondMatch = matcher.group(4);
                String fractionMatch = matcher.group(5);
                if (dayMatch != null || hourMatch != null || minuteMatch != null || secondMatch != null || fractionMatch != null) {
                    int daysAsMilliSecs = parseNumber(dayMatch, 86400000);
                    int hoursAsMilliSecs = parseNumber(hourMatch, 3600000);
                    int minutesAsMilliSecs = parseNumber(minuteMatch, '\uea60');
                    int secondsAsMilliSecs = parseNumber(secondMatch, 1000);
                    int milliseconds = parseNumber(fractionMatch, 1);
                    return (long) (daysAsMilliSecs + hoursAsMilliSecs + minutesAsMilliSecs + secondsAsMilliSecs + milliseconds);
                }
            }

            throw new ParserException(String.format("Text %s cannot be parsed to duration)", new Object[]{text}));
        }

        private static int parseNumber(String parsed, int multiplier) {
            return parsed != null && !parsed.trim().isEmpty() ? Integer.parseInt(parsed) * multiplier : 0;
        }
    }

    public static enum DateParser {
        INSTANCE;

        private static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
        private static final String MEDIUM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

        private DateParser() {
        }

        public Date parse(String text) throws ParserException {
            text = text.trim();
            int length = text.length();
            return length == "yyyy-MM-dd HH:mm:ss.SSS".length() ? this.parse(text, "yyyy-MM-dd HH:mm:ss.SSS") : (length == "yyyy-MM-dd HH:mm:ss".length() ? this.parse(text, "yyyy-MM-dd HH:mm:ss") : this.parse(text, "yyyy-MM-dd"));
        }

        public Date parse(String text, String format) throws ParserException {
            return this.parse(text, format, Locale.US);
        }

        public Date parse(String text, String format, Locale locale) throws ParserException {
            SimpleDateFormat dateFormat = this.getDateFormat(format, locale);

            try {
                return dateFormat.parse(text.trim());
            } catch (ParseException var6) {
                throw new ParserException("Error when parsing date(" + dateFormat.toPattern() + ") from " + text, var6);
            }
        }

        private SimpleDateFormat getDateFormat(String format, Locale locale) {
            return new SimpleDateFormat(format, locale);
        }
    }
}
