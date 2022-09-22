package io.github.gabbloquet.todolist.domain;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockRegistry {

    private final List<Object> mocks = new ArrayList<>();

    public <T> T mock(Class<T> mockedClass) {
        T mock = Mockito.mock(mockedClass);
        mocks.add(mock);
        return mock;
    }

    public <T> T spy(T spiedClass) {
        T spy = Mockito.spy(spiedClass);
        mocks.add(spy);
        return spy;
    }

    public void resetMocks() {
        mocks.forEach(Mockito::reset);
    }

}
