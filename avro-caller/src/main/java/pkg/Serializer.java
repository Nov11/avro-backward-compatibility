package pkg;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Serializer {
    public static <T> byte[] serialize(T t, Class<T> tClass) throws IOException {
        DatumWriter<T> writer = new SpecificDatumWriter<>(
                tClass);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        writer.write(t, binaryEncoder);
        binaryEncoder.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public static <T> T deserializer(byte[] bytes, Class<T> tClass) throws IOException {
        DatumReader<T> reader = new SpecificDatumReader<>(tClass);
        BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(bytes, null);
        return reader.read(null, binaryDecoder);
    }
}
