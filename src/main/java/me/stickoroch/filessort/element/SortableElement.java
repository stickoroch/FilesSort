package me.stickoroch.filessort.element;

import me.stickoroch.filessort.exception.DifferentTypeException;

public interface SortableElement {
    Object getElement();
    String toString();
    SortableElementType getType();
    boolean compare(SortableElement b) throws DifferentTypeException;
    boolean isEquals(SortableElement b) throws DifferentTypeException;
}
