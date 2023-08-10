package me.stickoroch.filessort.element;

import me.stickoroch.filessort.exception.DifferentTypeException;

public class IntegerSortableElement implements SortableElement{

    private final int element;

    public IntegerSortableElement(int element){
        this.element = element;
    }

    @Override
    public Object getElement() {
        return element;
    }

    @Override
    public SortableElementType getType() {
        return SortableElementType.INTEGER;
    }

    @Override
    public boolean compare(SortableElement b) throws DifferentTypeException {
        if(this.getType() != b.getType()) throw new DifferentTypeException(b.getType(), this.getType());
        return this.element > (int) b.getElement();
    }

    @Override
    public boolean isEquals(SortableElement b) throws DifferentTypeException {
        if(this.getType() != b.getType()) throw new DifferentTypeException(b.getType(), this.getType());
        return this.element == (int) b.getElement();
    }

    @Override
    public String toString(){
        return element+"";
    }
}
