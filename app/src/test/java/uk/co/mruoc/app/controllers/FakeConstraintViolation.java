package uk.co.mruoc.app.controllers;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.ArrayList;
import java.util.Iterator;

public class FakeConstraintViolation implements ConstraintViolation<String> {

    @Override
    public String getMessage() {
        return "fake constraint violation message";
    }

    @Override
    public String getMessageTemplate() {
        return null;
    }

    @Override
    public String getRootBean() {
        return null;
    }

    @Override
    public Class<String> getRootBeanClass() {
        return null;
    }

    @Override
    public Object getLeafBean() {
        return null;
    }

    @Override
    public Object[] getExecutableParameters() {
        return new Object[0];
    }

    @Override
    public Object getExecutableReturnValue() {
        return null;
    }

    @Override
    public Path getPropertyPath() {
        return new FakePath();
    }

    @Override
    public Object getInvalidValue() {
        return null;
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return null;
    }

    @Override
    public <U> U unwrap(Class<U> type) {
        return null;
    }

    @Override
    public String toString() {
        return getPropertyPath().toString();
    }

    private static class FakePath implements Path {

        @Override
        public Iterator<Node> iterator() {
            return new ArrayList<Node>().iterator();
        }

        @Override
        public String toString() {
            return "fake.property.path";
        }

    }

}
