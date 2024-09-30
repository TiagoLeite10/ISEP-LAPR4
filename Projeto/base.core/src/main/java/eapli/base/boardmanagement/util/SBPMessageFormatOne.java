package eapli.base.boardmanagement.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class SBPMessageFormatOne<S extends Serializable> implements SBPMessageFormat {

    private final byte version = 1;
    private final byte code;
    private final byte length1;
    private final byte length2;
    private byte[] serializedData;

    public SBPMessageFormatOne(DataInputStream inputStream) throws IOException {
        this.code = inputStream.readByte();
        this.length1 = inputStream.readByte();
        this.length2 = inputStream.readByte();
        this.serializedData = inputStream.readNBytes(this.dataSizeCalc());
    }

    public SBPMessageFormatOne(byte code, S data) {
        this.code = code;
        this.serializedData = SerializationUtils.serialize(data);
        this.length1 = this.length1();
        this.length2 = this.length2();
        this.fillAllRequiredBytes();
    }

    public SBPMessageFormatOne(byte code, ArrayList<S> data) {
        this.code = code;
        this.serializedData = SerializationUtils.serialize(data);
        this.length1 = this.length1();
        this.length2 = this.length2();
        this.fillAllRequiredBytes();
    }

    @Override
    public int version() {
        return Byte.toUnsignedInt(version);
    }

    @Override
    public int code() {
        return Byte.toUnsignedInt(code);
    }

    @Override
    public S data() {
        return SerializationUtils.deserialize(this.serializedData);
    }

    @Override
    public ArrayList<S> dataArray() {
        return SerializationUtils.deserialize(this.serializedData);
    }

    @Override
    public byte[] toMessageFormat() {
        return ArrayUtils.addAll(new byte[]{
                this.version,
                this.code,
                this.length1,
                this.length2
        }, this.serializedData);
    }

    private int dataSizeCalc() {
        return Byte.toUnsignedInt(this.length1) + 255 * Byte.toUnsignedInt(this.length2);
    }

    private byte length1() {
        int length = this.serializedData.length;

        if (length > 255) {
            length = 255;
        }

        return (byte) length;
    }

    private byte length2() {
        int length = this.serializedData.length;

        if (length <= 255) {
            return 0;
        }

        int value = 0;
        for (int i = 255; i < length; i += 255) {
            value++;
        }
        
        return (byte)value;
    }

    private void fillAllRequiredBytes() {
        int requiredLength = this.dataSizeCalc();
        if (requiredLength != serializedData.length) {
            int clearBytes = requiredLength - serializedData.length;
            for (int i = 0; i < clearBytes; i++) {
                this.serializedData = ArrayUtils.add(this.serializedData, (byte)'\0');
            }
        }
    }
}
