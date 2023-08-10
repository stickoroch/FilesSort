package me.stickoroch.filessort.element;

import me.stickoroch.filessort.exception.DifferentTypeException;

public class StringSortableElement implements SortableElement{

    private final String element;

    public StringSortableElement(String element){
        this.element = element;
    }

    @Override
    public Object getElement() {
        return element;
    }

    @Override
    public SortableElementType getType() {
        return SortableElementType.STRING;
    }

    @Override
    public boolean compare(SortableElement b) throws DifferentTypeException {
        if(this.getType() != b.getType()) throw new DifferentTypeException(b.getType(), this.getType());
        return this.element.compareTo((String) b.getElement()) > 0;
    }

    @Override
    public boolean isEquals(SortableElement b) throws DifferentTypeException {
        if(this.getType() != b.getType()) throw new DifferentTypeException(b.getType(), this.getType());
        return this.element.equals(b.getElement());
    }

    @Override
    public String toString(){
        return element;
    }
}
