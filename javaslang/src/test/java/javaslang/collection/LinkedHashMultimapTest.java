package javaslang.collection;

import javaslang.Tuple2;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class LinkedHashMultimapTest extends AbstractMultimapTest {

    @Override
    protected String className() {
        return "Multimap[LinkedHashMap," + containerName() + "]";
    }

    @Override
    protected <T1, T2> Multimap<T1, T2> emptyMap() {
        switch (containerType) {
            case SEQ:
                return LinkedHashMultimap.withSeq().empty();
            case SET:
                return LinkedHashMultimap.withSet().empty();
            case SORTED_SET:
                return LinkedHashMultimap.withSortedSet(TreeSetTest.toStringComparator()).empty();
        }
        throw new RuntimeException();
    }

    @Override
    protected <T> Collector<Tuple2<Integer, T>, ArrayList<Tuple2<Integer, T>>, ? extends Multimap<Integer, T>> mapCollector() {
        switch (containerType) {
            case SEQ:
                return LinkedHashMultimap.withSeq().collector();
            case SET:
                return LinkedHashMultimap.withSet().collector();
            case SORTED_SET:
                return LinkedHashMultimap.withSortedSet(TreeSetTest.toStringComparator()).collector();
        }
        throw new RuntimeException();
    }

    @SuppressWarnings("varargs")
    @SafeVarargs
    @Override
    protected final <K, V> Multimap<K, V> mapOfTuples(Tuple2<? extends K, ? extends V>... entries) {
        switch (containerType) {
            case SEQ:
                return LinkedHashMultimap.withSeq().ofEntries(entries);
            case SET:
                return LinkedHashMultimap.withSet().ofEntries(entries);
            case SORTED_SET:
                return LinkedHashMultimap.withSortedSet(TreeSetTest.toStringComparator()).ofEntries(entries);
        }
        throw new RuntimeException();
    }

    @SuppressWarnings("varargs")
    @SafeVarargs
    @Override
    protected final <K, V> Multimap<K, V> mapOfEntries(Map.Entry<? extends K, ? extends V>... entries) {
        switch (containerType) {
            case SEQ:
                return LinkedHashMultimap.withSeq().ofEntries(entries);
            case SET:
                return LinkedHashMultimap.withSet().ofEntries(entries);
            case SORTED_SET:
                return LinkedHashMultimap.withSortedSet(TreeSetTest.toStringComparator()).ofEntries(entries);
        }
        throw new RuntimeException();
    }

    @Override
    protected <K, V> Multimap<K, V> mapOfPairs(Object... pairs) {
        switch (containerType) {
            case SEQ:
                return LinkedHashMultimap.withSeq().of(pairs);
            case SET:
                return LinkedHashMultimap.withSet().of(pairs);
            case SORTED_SET:
                return LinkedHashMultimap.withSortedSet(TreeSetTest.toStringComparator()).of(pairs);
        }
        throw new RuntimeException();
    }

    @Override
    protected <K, V> Multimap<K, V> mapOf(K key, V value) {
        final Multimap<K, V> map = emptyMap();
        return map.put(key, value);
    }

    @Override
    protected <K, V> Multimap<K, V> mapTabulate(int n, Function<? super Integer, ? extends Tuple2<? extends K, ? extends V>> f) {
        switch (containerType) {
            case SEQ:
                return LinkedHashMultimap.withSeq().tabulate(n, f);
            case SET:
                return LinkedHashMultimap.withSet().tabulate(n, f);
            case SORTED_SET:
                return LinkedHashMultimap.withSortedSet(TreeSetTest.toStringComparator()).tabulate(n, f);
        }
        throw new RuntimeException();
    }

    @Override
    protected <K, V> Multimap<K, V> mapFill(int n, Supplier<? extends Tuple2<? extends K, ? extends V>> s) {
        switch (containerType) {
            case SEQ:
                return LinkedHashMultimap.withSeq().fill(n, s);
            case SET:
                return LinkedHashMultimap.withSet().fill(n, s);
            case SORTED_SET:
                return LinkedHashMultimap.withSortedSet(TreeSetTest.toStringComparator()).fill(n, s);
        }
        throw new RuntimeException();
    }

    // -- narrow

    @Test
    public void shouldNarrowMap() {
        final LinkedHashMultimap<Integer, Number> int2doubleMap = (LinkedHashMultimap<Integer, Number>) this.<Integer, Number>emptyMap().put(1, 1.0d);
        final LinkedHashMultimap<Number, Number> number2numberMap = LinkedHashMultimap.narrow(int2doubleMap);
        final int actual = number2numberMap.put(new BigDecimal("2"), new BigDecimal("2.0")).values().sum().intValue();
        assertThat(actual).isEqualTo(3);
    }

}