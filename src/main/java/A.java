import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import com.aerospike.mapper.annotations.AerospikeReference;

import java.util.HashMap;
import java.util.Map;

@AerospikeRecord(namespace = "test", set = "a")
public class A {

    @AerospikeKey
    public int id;

    @AerospikeReference
    public Map<B, Integer> bIntegerMap = new HashMap<>();


}
