package com.kaizhuo.data.jpa.support;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.data.jpa.support
 * @description:
 * @author: miaochen
 * @create: 2020-02-23 22:48
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class Range<E> implements Serializable {

    private static final long serialVersionUID = -2346734204124131483L;

    private String field;
    private Comparable from;
    private Comparable to;
    private Boolean includeNull;

    public Range(String field) {
        this.field = field;
    }

    public Range(String field, Comparable from, Comparable to) {
        this.field = field;
        this.from = from;
        this.to = to;
    }

    public Range(String field, Comparable from, Comparable to, Boolean includeNull) {
        this.field = field;
        this.from = from;
        this.to = to;
        this.includeNull = includeNull;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Comparable getFrom() {
        return from;
    }

    public void setFrom(Comparable from) {
        this.from = from;
    }

    public Comparable getTo() {
        return to;
    }

    public void setTo(Comparable to) {
        this.to = to;
    }

    public Boolean getIncludeNull() {
        return includeNull;
    }

    public void setIncludeNull(Boolean includeNull) {
        this.includeNull = includeNull;
    }
    public boolean isFromSet() {
        return getFrom() != null;
    }

    public boolean isToSet() {
        return getTo() != null;
    }

    public boolean isIncludeNullSet() {
        return includeNull != null;
    }

    public boolean isBetween() {
        return isFromSet() && isToSet();
    }

    public boolean isSet() {
        return isFromSet() || isToSet() || isIncludeNullSet();
    }

    public boolean isValid() {
        if (isBetween()) {
            return getFrom().compareTo(getTo()) <= 0;
        }

        return true;
    }
}
