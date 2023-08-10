package me.stickoroch.filessort.exception;

import me.stickoroch.filessort.element.SortableElementType;

public class DifferentTypeException extends Exception{

    public DifferentTypeException(SortableElementType provided, SortableElementType requirement){
        super("Разные типы данных! Ожидался "+requirement.toString()+", получен "+provided);
    }
}
