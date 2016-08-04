// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common/src/main/proto/config.proto

package com.nilhcem.hexawatch.common.config.services.model.proto;

public final class Config {
  private Config() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ModelOrBuilder extends
      // @@protoc_insertion_point(interface_extends:hexawatch.Model)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string preset = 1;</code>
     */
    java.lang.String getPreset();
    /**
     * <code>optional string preset = 1;</code>
     */
    com.google.protobuf.ByteString
        getPresetBytes();

    /**
     * <code>optional int32 bg_color = 2;</code>
     */
    int getBgColor();

    /**
     * <code>optional int32 fill_color = 3;</code>
     */
    int getFillColor();

    /**
     * <code>optional int32 stroke_color = 4;</code>
     */
    int getStrokeColor();

    /**
     * <code>optional float stroke_width = 5;</code>
     */
    float getStrokeWidth();

    /**
     * <code>optional float inner_hexa_ratio = 6;</code>
     */
    float getInnerHexaRatio();
  }
  /**
   * Protobuf type {@code hexawatch.Model}
   */
  public  static final class Model extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:hexawatch.Model)
      ModelOrBuilder {
    // Use Model.newBuilder() to construct.
    private Model(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Model() {
      preset_ = "";
      bgColor_ = 0;
      fillColor_ = 0;
      strokeColor_ = 0;
      strokeWidth_ = 0F;
      innerHexaRatio_ = 0F;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private Model(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              preset_ = s;
              break;
            }
            case 16: {

              bgColor_ = input.readInt32();
              break;
            }
            case 24: {

              fillColor_ = input.readInt32();
              break;
            }
            case 32: {

              strokeColor_ = input.readInt32();
              break;
            }
            case 45: {

              strokeWidth_ = input.readFloat();
              break;
            }
            case 53: {

              innerHexaRatio_ = input.readFloat();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.nilhcem.hexawatch.common.config.services.model.proto.Config.internal_static_hexawatch_Model_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.nilhcem.hexawatch.common.config.services.model.proto.Config.internal_static_hexawatch_Model_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model.class, com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model.Builder.class);
    }

    public static final int PRESET_FIELD_NUMBER = 1;
    private volatile java.lang.Object preset_;
    /**
     * <code>optional string preset = 1;</code>
     */
    public java.lang.String getPreset() {
      java.lang.Object ref = preset_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        preset_ = s;
        return s;
      }
    }
    /**
     * <code>optional string preset = 1;</code>
     */
    public com.google.protobuf.ByteString
        getPresetBytes() {
      java.lang.Object ref = preset_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        preset_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BG_COLOR_FIELD_NUMBER = 2;
    private int bgColor_;
    /**
     * <code>optional int32 bg_color = 2;</code>
     */
    public int getBgColor() {
      return bgColor_;
    }

    public static final int FILL_COLOR_FIELD_NUMBER = 3;
    private int fillColor_;
    /**
     * <code>optional int32 fill_color = 3;</code>
     */
    public int getFillColor() {
      return fillColor_;
    }

    public static final int STROKE_COLOR_FIELD_NUMBER = 4;
    private int strokeColor_;
    /**
     * <code>optional int32 stroke_color = 4;</code>
     */
    public int getStrokeColor() {
      return strokeColor_;
    }

    public static final int STROKE_WIDTH_FIELD_NUMBER = 5;
    private float strokeWidth_;
    /**
     * <code>optional float stroke_width = 5;</code>
     */
    public float getStrokeWidth() {
      return strokeWidth_;
    }

    public static final int INNER_HEXA_RATIO_FIELD_NUMBER = 6;
    private float innerHexaRatio_;
    /**
     * <code>optional float inner_hexa_ratio = 6;</code>
     */
    public float getInnerHexaRatio() {
      return innerHexaRatio_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getPresetBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, preset_);
      }
      if (bgColor_ != 0) {
        output.writeInt32(2, bgColor_);
      }
      if (fillColor_ != 0) {
        output.writeInt32(3, fillColor_);
      }
      if (strokeColor_ != 0) {
        output.writeInt32(4, strokeColor_);
      }
      if (strokeWidth_ != 0F) {
        output.writeFloat(5, strokeWidth_);
      }
      if (innerHexaRatio_ != 0F) {
        output.writeFloat(6, innerHexaRatio_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getPresetBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, preset_);
      }
      if (bgColor_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, bgColor_);
      }
      if (fillColor_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, fillColor_);
      }
      if (strokeColor_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, strokeColor_);
      }
      if (strokeWidth_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(5, strokeWidth_);
      }
      if (innerHexaRatio_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(6, innerHexaRatio_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model)) {
        return super.equals(obj);
      }
      com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model other = (com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model) obj;

      boolean result = true;
      result = result && getPreset()
          .equals(other.getPreset());
      result = result && (getBgColor()
          == other.getBgColor());
      result = result && (getFillColor()
          == other.getFillColor());
      result = result && (getStrokeColor()
          == other.getStrokeColor());
      result = result && (
          java.lang.Float.floatToIntBits(getStrokeWidth())
          == java.lang.Float.floatToIntBits(
              other.getStrokeWidth()));
      result = result && (
          java.lang.Float.floatToIntBits(getInnerHexaRatio())
          == java.lang.Float.floatToIntBits(
              other.getInnerHexaRatio()));
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + PRESET_FIELD_NUMBER;
      hash = (53 * hash) + getPreset().hashCode();
      hash = (37 * hash) + BG_COLOR_FIELD_NUMBER;
      hash = (53 * hash) + getBgColor();
      hash = (37 * hash) + FILL_COLOR_FIELD_NUMBER;
      hash = (53 * hash) + getFillColor();
      hash = (37 * hash) + STROKE_COLOR_FIELD_NUMBER;
      hash = (53 * hash) + getStrokeColor();
      hash = (37 * hash) + STROKE_WIDTH_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getStrokeWidth());
      hash = (37 * hash) + INNER_HEXA_RATIO_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getInnerHexaRatio());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code hexawatch.Model}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:hexawatch.Model)
        com.nilhcem.hexawatch.common.config.services.model.proto.Config.ModelOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.nilhcem.hexawatch.common.config.services.model.proto.Config.internal_static_hexawatch_Model_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.nilhcem.hexawatch.common.config.services.model.proto.Config.internal_static_hexawatch_Model_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model.class, com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model.Builder.class);
      }

      // Construct using com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        preset_ = "";

        bgColor_ = 0;

        fillColor_ = 0;

        strokeColor_ = 0;

        strokeWidth_ = 0F;

        innerHexaRatio_ = 0F;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.nilhcem.hexawatch.common.config.services.model.proto.Config.internal_static_hexawatch_Model_descriptor;
      }

      public com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model getDefaultInstanceForType() {
        return com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model.getDefaultInstance();
      }

      public com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model build() {
        com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model buildPartial() {
        com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model result = new com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model(this);
        result.preset_ = preset_;
        result.bgColor_ = bgColor_;
        result.fillColor_ = fillColor_;
        result.strokeColor_ = strokeColor_;
        result.strokeWidth_ = strokeWidth_;
        result.innerHexaRatio_ = innerHexaRatio_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model) {
          return mergeFrom((com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model other) {
        if (other == com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model.getDefaultInstance()) return this;
        if (!other.getPreset().isEmpty()) {
          preset_ = other.preset_;
          onChanged();
        }
        if (other.getBgColor() != 0) {
          setBgColor(other.getBgColor());
        }
        if (other.getFillColor() != 0) {
          setFillColor(other.getFillColor());
        }
        if (other.getStrokeColor() != 0) {
          setStrokeColor(other.getStrokeColor());
        }
        if (other.getStrokeWidth() != 0F) {
          setStrokeWidth(other.getStrokeWidth());
        }
        if (other.getInnerHexaRatio() != 0F) {
          setInnerHexaRatio(other.getInnerHexaRatio());
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object preset_ = "";
      /**
       * <code>optional string preset = 1;</code>
       */
      public java.lang.String getPreset() {
        java.lang.Object ref = preset_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          preset_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string preset = 1;</code>
       */
      public com.google.protobuf.ByteString
          getPresetBytes() {
        java.lang.Object ref = preset_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          preset_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string preset = 1;</code>
       */
      public Builder setPreset(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        preset_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string preset = 1;</code>
       */
      public Builder clearPreset() {
        
        preset_ = getDefaultInstance().getPreset();
        onChanged();
        return this;
      }
      /**
       * <code>optional string preset = 1;</code>
       */
      public Builder setPresetBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        preset_ = value;
        onChanged();
        return this;
      }

      private int bgColor_ ;
      /**
       * <code>optional int32 bg_color = 2;</code>
       */
      public int getBgColor() {
        return bgColor_;
      }
      /**
       * <code>optional int32 bg_color = 2;</code>
       */
      public Builder setBgColor(int value) {
        
        bgColor_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 bg_color = 2;</code>
       */
      public Builder clearBgColor() {
        
        bgColor_ = 0;
        onChanged();
        return this;
      }

      private int fillColor_ ;
      /**
       * <code>optional int32 fill_color = 3;</code>
       */
      public int getFillColor() {
        return fillColor_;
      }
      /**
       * <code>optional int32 fill_color = 3;</code>
       */
      public Builder setFillColor(int value) {
        
        fillColor_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 fill_color = 3;</code>
       */
      public Builder clearFillColor() {
        
        fillColor_ = 0;
        onChanged();
        return this;
      }

      private int strokeColor_ ;
      /**
       * <code>optional int32 stroke_color = 4;</code>
       */
      public int getStrokeColor() {
        return strokeColor_;
      }
      /**
       * <code>optional int32 stroke_color = 4;</code>
       */
      public Builder setStrokeColor(int value) {
        
        strokeColor_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 stroke_color = 4;</code>
       */
      public Builder clearStrokeColor() {
        
        strokeColor_ = 0;
        onChanged();
        return this;
      }

      private float strokeWidth_ ;
      /**
       * <code>optional float stroke_width = 5;</code>
       */
      public float getStrokeWidth() {
        return strokeWidth_;
      }
      /**
       * <code>optional float stroke_width = 5;</code>
       */
      public Builder setStrokeWidth(float value) {
        
        strokeWidth_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional float stroke_width = 5;</code>
       */
      public Builder clearStrokeWidth() {
        
        strokeWidth_ = 0F;
        onChanged();
        return this;
      }

      private float innerHexaRatio_ ;
      /**
       * <code>optional float inner_hexa_ratio = 6;</code>
       */
      public float getInnerHexaRatio() {
        return innerHexaRatio_;
      }
      /**
       * <code>optional float inner_hexa_ratio = 6;</code>
       */
      public Builder setInnerHexaRatio(float value) {
        
        innerHexaRatio_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional float inner_hexa_ratio = 6;</code>
       */
      public Builder clearInnerHexaRatio() {
        
        innerHexaRatio_ = 0F;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:hexawatch.Model)
    }

    // @@protoc_insertion_point(class_scope:hexawatch.Model)
    private static final com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model();
    }

    public static com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Model>
        PARSER = new com.google.protobuf.AbstractParser<Model>() {
      public Model parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Model(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Model> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Model> getParserForType() {
      return PARSER;
    }

    public com.nilhcem.hexawatch.common.config.services.model.proto.Config.Model getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_hexawatch_Model_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_hexawatch_Model_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"common/src/main/proto/config.proto\022\the" +
      "xawatch\"\203\001\n\005Model\022\016\n\006preset\030\001 \001(\t\022\020\n\010bg_" +
      "color\030\002 \001(\005\022\022\n\nfill_color\030\003 \001(\005\022\024\n\014strok" +
      "e_color\030\004 \001(\005\022\024\n\014stroke_width\030\005 \001(\002\022\030\n\020i" +
      "nner_hexa_ratio\030\006 \001(\002BB\n8com.nilhcem.hex" +
      "awatch.common.config.services.model.prot" +
      "oB\006Configb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_hexawatch_Model_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_hexawatch_Model_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_hexawatch_Model_descriptor,
        new java.lang.String[] { "Preset", "BgColor", "FillColor", "StrokeColor", "StrokeWidth", "InnerHexaRatio", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
