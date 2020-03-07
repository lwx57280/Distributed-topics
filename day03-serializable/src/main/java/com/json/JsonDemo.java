package com.json;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.domain.Person;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * JSON序列化
 */
public class JsonDemo {

    private static Person init() {
        Person person = new Person();
        person.setName("mic");
        person.setAge(14);
        return person;
    }

    public static void main(String[] args) {
        try {
            excuteWithJack();
            excuteWithFastJson();
            excuteWithProtoBuf();
            excuteWithHessian();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void excuteWithJack() throws IOException {
        Person p = init();
        ObjectMapper mapper = new ObjectMapper();
        byte[] writBytes = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            writBytes = mapper.writeValueAsBytes(p);
        }
        System.out.println("excuteWithJack Json序列化:" + (System.currentTimeMillis() - start) + "ms:" +
                "总大小-》" + writBytes.length);
        Person person = mapper.readValue(writBytes, Person.class);
        System.out.println(person);

    }

    /**
     * 阿里fastJson
     * @throws IOException
     */
    private static void excuteWithFastJson() throws IOException {
        Person p = init();
        String text = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            text = JSON.toJSONString(p);
        }
        System.out.println("excuteWithFastJson Json序列化:" + (System.currentTimeMillis() - start) + "ms:" +
                "总大小-》" + text.getBytes().length);
        Person person = JSON.parseObject(text, Person.class);
        System.out.println(person);

    }
    /**
     * 百度封装ProtoBuf
     * @throws IOException
     */
    private static void excuteWithProtoBuf() throws IOException {
        Person p = init();
        Codec<Person> personCodec = ProtobufProxy.create(Person.class,false);
        long start = System.currentTimeMillis();

        byte[] bytes = null;
        for (int i = 0; i < 10000; i++) {
            bytes = personCodec.encode(p);
        }
        System.out.println("excuteWithProtoBuf Json序列化:" + (System.currentTimeMillis() - start) + "ms:" +
                "总大小-》" + bytes.length);
        Person person = personCodec.decode(bytes);
        System.out.println(person);

    }


    private static void excuteWithHessian() throws IOException {
        Person p = init();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            ho.writeObject(p);
            if (i == 0) {
                System.out.println(os.toByteArray().length);
            }
        }
        System.out.println("Hessian Json序列化:" + (System.currentTimeMillis() - start) + "ms:" +
                "总大小-》" +os.toByteArray().length);
        HessianInput hi = new HessianInput(new ByteArrayInputStream(os.toByteArray()));
        Person person = (Person)hi.readObject();
        System.out.println(person);

    }
}



