package com.zhilianhua.ssicp.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
//@EnableCaching
public class RedisConfig {

//    public class InstantSerializer extends JsonSerializer<Instant> {
//        @Override
//        public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//            if (instant != null) {
//                jsonGenerator.writeNumber(instant.toEpochMilli());
//            }
//        }
//    }
//
//    public class InstantDeserializer extends JsonDeserializer<Instant> {
//        @Override
//        public Instant deserialize(JsonParser jsonParser, DeserializationContext
//                deserializationContext)
//                throws IOException {
//            long timestamp = jsonParser.getLongValue();
//            if (timestamp > 0) {
//                return Instant.ofEpochMilli(timestamp);
//            } else {
//                return null;
//            }
//        }
//    }

//    private ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule
//        objectMapper.registerModule(new SimpleModule().addDeserializer(Instant.class, new InstantDeserializer()).addSerializer(Instant.class, new InstantSerializer()));
//        return objectMapper;
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setConnectionFactory(factory);
        template.setDefaultSerializer(stringRedisSerializer);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
//
//    @Bean
//    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
//        return redisTemplate.opsForHash();
//    }
//
//    @Bean
//    public RedisCacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate) {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
//                .defaultCacheConfig()
//                .prefixCacheNameWith("ssicp::")
//                // 设置key为String
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getStringSerializer()))
//                // 设置value 为自动转Json的Object
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
//                .entryTtl(Duration.ofMillis(-1))
//                // 不缓存null
//                .disableCachingNullValues();
//        // 构造一个redis缓存管理器
//        RedisCacheManager redisCacheManager = RedisCacheManager
//                .RedisCacheManagerBuilder
//                // Redis 连接工厂
//                .fromConnectionFactory(redisTemplate.getConnectionFactory())
//                // 设置默认缓存配置
//                .cacheDefaults(redisCacheConfiguration)
//                // 配置同步修改或删除 put/evict
//                .transactionAware()
//                .build();
//        return redisCacheManager;
//    }
}