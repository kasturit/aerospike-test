import com.aerospike.client.AerospikeClient;
import com.aerospike.mapper.tools.AeroMapper;

public class TestMapper {

    public static void main(String[] args) {
        AerospikeClient client = new AerospikeClient("localhost", 3000);
        AeroMapper mapper = new AeroMapper.Builder(client).build();

        B b = new B();
        b.id = 23;
        mapper.save(b);

        B b2 = mapper.read(B.class, 23);
        System.out.println("b2 id is " + b2.id);

        A a = new A();
        a.id = 56;
        a.bIntegerMap.put(b, 4);
        // This saves properly, as a reference
        mapper.save(a);
        // Output from aql:
        // aql> select * from test.a
        // +---------------------------+----+
        // | bIntegerMap               | id |
        // +---------------------------+----+
        // | KEY_ORDERED_MAP('{23:4}') | 56 |
        // +---------------------------+----+
        // 1 row in set (0.009 secs)

        // This fails:
        A a2 = mapper.read(A.class, 56);
        // Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class com.aerospike.mapper.tools.DeferredObjectLoader$DeferredObject (java.lang.Integer is in module java.base of loader 'bootstrap'; com.aerospike.mapper.tools.DeferredObjectLoader$DeferredObject is in unnamed module of loader 'app')
        //	at com.aerospike.mapper.tools.mappers.MapMapper.fromAerospikeFormat(MapMapper.java:80)
        //	at com.aerospike.mapper.tools.ClassCacheEntry.constructAndHydrate(ClassCacheEntry.java:949)
        //	at com.aerospike.mapper.tools.ClassCacheEntry.constructAndHydrate(ClassCacheEntry.java:924)
        //	at com.aerospike.mapper.tools.converters.MappingConverter.convertToObject(MappingConverter.java:110)
        //	at com.aerospike.mapper.tools.AeroMapper.read(AeroMapper.java:347)
        //	at com.aerospike.mapper.tools.AeroMapper.read(AeroMapper.java:281)
        //	at com.aerospike.mapper.tools.AeroMapper.read(AeroMapper.java:273)
        //	at TestMapper.main(TestMapper.java:31)







    }
}
