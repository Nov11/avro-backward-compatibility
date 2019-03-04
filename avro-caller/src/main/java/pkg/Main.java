package pkg;

import nov11.DumbClass;

import java.io.*;

public class Main {
    private static String fileName = "ori";

    private static DumbClass makeDumbObject() {
        DumbClass dumbClass = new DumbClass();
        dumbClass.setId(1111);
        dumbClass.setName("name");
        return dumbClass;
    }

    private static void writeToFile(String fileName, byte[] bytes) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    private static byte[] readFromFile(String fileName) throws IOException {
        File input = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(input);
        byte[] bytes = new byte[(int) input.length()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }

    /**
     * this serialize an object to a file and then read from it.
     * compares the original object with the one we get from deserialization to make sure that the code works.
     * this also write an object of DumbClass with two fields to file.
     *
     * @throws IOException
     */
    private static void makeSureSerailizationWorks() throws IOException {
        DumbClass dumbClass = makeDumbObject();
        writeToFile(fileName, Serializer.serialize(dumbClass, DumbClass.class));
        DumbClass deserialized = Serializer.deserializer(readFromFile(fileName), DumbClass.class);
        System.out.println("read & write from file works: " + deserialized.equals(dumbClass));
    }

    private static void readOldObjectUsingNewDef() throws IOException {
        DumbClass deserialized = Serializer.deserializer(readFromFile(fileName), DumbClass.class);
        System.out.println("backward compatibility : " + deserialized.equals(makeDumbObject()));
    }

    public static void main(String[] args) throws IOException {
        // this tests the serializer and generates the output file.
//        makeSureSerailizationWorks();
        readOldObjectUsingNewDef();
    }
}
