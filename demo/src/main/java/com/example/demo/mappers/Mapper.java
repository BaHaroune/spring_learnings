package com.example.demo.mappers;

public interface Mapper<A, B> {
    B toDto(A a);
    A toEntity(B b);
}
