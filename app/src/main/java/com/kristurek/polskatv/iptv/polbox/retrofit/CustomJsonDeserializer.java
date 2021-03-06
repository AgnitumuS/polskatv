package com.kristurek.polskatv.iptv.polbox.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.kristurek.polskatv.iptv.polbox.pojo.error.ErrorRetrofitResponse;
import com.kristurek.polskatv.iptv.util.Tag;

import java.lang.reflect.Type;

public class CustomJsonDeserializer<T> implements JsonDeserializer<T> {

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(final JsonElement json, final Type typeOfT,
                         final JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.has("error"))
                return (T) new Gson().fromJson(json, ErrorRetrofitResponse.class);

            return new GsonBuilder().setLenient().create().fromJson(json, typeOfT);
        } else {
            Log.e(Tag.API, "Not a JSON Object. [" + json.getAsString() + "]");
            throw new IllegalStateException("Not a JSON Object. [" + json.getAsString() + "]");
        }
    }
}
