package by.teplouhova.webservice;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;

/**
 * Class contain converter work with YAML request and response messages
 */

public class YamlMessageConverter<T> extends AbstractHttpMessageConverter<T> {

    public YamlMessageConverter() {
        super(new MediaType("application", "yaml"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected T readInternal(Class<? extends T> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Yaml yaml = new Yaml(new Constructor(aClass));
        T object = (T) yaml.load(httpInputMessage.getBody());
        return object;
    }

    @Override
    protected void writeInternal(T t, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Yaml yaml = new Yaml();
        String result = yaml.dump(t);
        httpOutputMessage.getBody().write(result.getBytes());
    }
}
