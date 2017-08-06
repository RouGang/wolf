package com.special.wolf.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.List;

/**
 * Created by Hikaru on 17/8/6.
 * // to enable standard indentation ("pretty-printing"):
 * mapper.enable(SerializationFeature.INDENT_OUTPUT);
 * // to allow serialization of "empty" POJOs (no properties to serialize)
 * // (without this setting, an exception is thrown in those cases)
 * mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
 * // to write java.util.Date, Calendar as number (timestamp):
 * mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
 *
 * // DeserializationFeature for changing how JSON is read as POJOs:
 *
 * // to prevent exception when encountering unknown property:
 * mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
 * // to allow coercion of JSON empty String ("") to null Object value:
 * mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
 *
 * // =========================================================================
 * // JsonParser.Feature for configuring parsing settings:
 *
 * // to allow C/C++ style comments in JSON (non-standard, disabled by default)
 * // (note: with Jackson 2.5, there is also `mapper.enable(feature)` / `mapper.disable(feature)`)
 * mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
 * // to allow (non-standard) unquoted field names in JSON:
 * mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
 * // to allow use of apostrophes (single quotes), non standard
 * mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
 *
 * // JsonGenerator.Feature for configuring low-level JSON generation:
 *
 * // to force escaping of non-ASCII characters:
 * mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
 */
public final class JsonUtil {

  static final ObjectMapper MAPPER = new ObjectMapper()
      .registerModule(new Jdk8Module())
      .registerModule(new JavaTimeModule())
      .registerModule(new GuavaModule())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

  /**
   * 将对象转换为json字符串
   */
  public static <T> String obj2string(T t) {
    try {
      return MAPPER.writeValueAsString(t);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 将字符串转list对象
   */
  public static <T> List<T> str2list(String jsonStr, Class<T> cls) {
    ObjectMapper mapper = new ObjectMapper();
    List<T> objList = null;
    try {
      JavaType t = mapper.getTypeFactory().constructParametricType(
          List.class, cls);
      objList = mapper.readValue(jsonStr, t);
    } catch (Exception e) {
    }
    return objList;
  }

  /**
   * 将字符串转为对象
   */
  public static <T> T str2obj(String jsonStr, Class<T> cls) {
    ObjectMapper mapper = new ObjectMapper();
    T obj = null;
    try {
      obj = mapper.readValue(jsonStr, cls);
    } catch (Exception e) {
    }
    return obj;
  }


  /**
   * 将字符串转为json节点
   */
  public static JsonNode str2node(String jsonStr) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readTree(jsonStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <T> T readAs(byte[] bytes, TypeReference<T> typeReference) {
    try {
      return MAPPER.readValue(bytes, typeReference);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}
