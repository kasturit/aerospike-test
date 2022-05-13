# aerospike-test

Demonstration of error in Aerospike Java Object Mapper.

Map of Aerospike record object to Java object will store properly,
but will not read back properly.

See [A](src/main/java/A.java) and [TestMapper](src/main/java/TestMapper.java)

Writing works fine, but reading fails with the following error:
```
Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class com.aerospike.mapper.tools.DeferredObjectLoader$DeferredObject (java.lang.Integer is in module java.base of loader 'bootstrap'; com.aerospike.mapper.tools.DeferredObjectLoader$DeferredObject is in unnamed module of loader 'app')
at com.aerospike.mapper.tools.mappers.MapMapper.fromAerospikeFormat(MapMapper.java:80)
at com.aerospike.mapper.tools.ClassCacheEntry.constructAndHydrate(ClassCacheEntry.java:949)
at com.aerospike.mapper.tools.ClassCacheEntry.constructAndHydrate(ClassCacheEntry.java:924)
at com.aerospike.mapper.tools.converters.MappingConverter.convertToObject(MappingConverter.java:110)
at com.aerospike.mapper.tools.AeroMapper.read(AeroMapper.java:347)
at com.aerospike.mapper.tools.AeroMapper.read(AeroMapper.java:281)
at com.aerospike.mapper.tools.AeroMapper.read(AeroMapper.java:273)
at TestMapper.main(TestMapper.java:32)
```