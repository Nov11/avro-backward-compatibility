#### this is about AVRO backward compatibility

An object DumbClass defined in jar named 1.0-SNAPSHOT is written to file first.
Update idl with a new field and named it 1.1-SNAPSHOT.
Read object using the new jar. It fails with this :
```
Exception in thread "main" java.io.EOFException
	at org.apache.avro.io.BinaryDecoder.ensureBounds(BinaryDecoder.java:473)
	at org.apache.avro.io.BinaryDecoder.readInt(BinaryDecoder.java:128)
	at org.apache.avro.io.BinaryDecoder.readIndex(BinaryDecoder.java:423)
	at org.apache.avro.io.ResolvingDecoder.doAction(ResolvingDecoder.java:290)
	at org.apache.avro.io.parsing.Parser.advance(Parser.java:88)
	at org.apache.avro.io.ResolvingDecoder.readIndex(ResolvingDecoder.java:267)
	at org.apache.avro.generic.GenericDatumReader.read(GenericDatumReader.java:155)
	at org.apache.avro.generic.GenericDatumReader.readField(GenericDatumReader.java:193)
	at org.apache.avro.generic.GenericDatumReader.readRecord(GenericDatumReader.java:183)
	at org.apache.avro.generic.GenericDatumReader.read(GenericDatumReader.java:151)
	at org.apache.avro.generic.GenericDatumReader.read(GenericDatumReader.java:142)
	at pkg.Serializer.deserializer(Serializer.java:24)
	at pkg.Main.readOldObjectUsingNewDef(Main.java:47)
	at pkg.Main.main(Main.java:52)

Process finished with exit code 1
```


I haven't found any flaws in serialization code nor idl. So I think AVRO doesn't support backward compatibility.

top level pom & idl's pom are modified during this test.
