package com.poseal.university.service.mappers;

import java.util.List;

public interface Mapper<K, V> {

    public K transformToDto(V v);

    public V transformToModel(K k);

    public List<K> transformToListDto(List<V> v);

    public List<V> transformToListModel(List<K> k);

}

