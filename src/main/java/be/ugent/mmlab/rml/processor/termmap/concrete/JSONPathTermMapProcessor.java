package be.ugent.mmlab.rml.processor.termmap.concrete;

import be.ugent.mmlab.rml.processor.termmap.AbstractTermMapProcessor;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 *
 * @author andimou
 */
public class JSONPathTermMapProcessor extends AbstractTermMapProcessor {
    
    // Log
    private static final Logger log = LoggerFactory.getLogger(JSONPathTermMapProcessor.class);
    
    @Override
    public List<String> extractValueFromNode(Object node, String expression) {
        
        try {
            Object val = JsonPath.read(node, expression);
            List<String> list = new ArrayList<>();
            if (val instanceof JSONArray) {
                JSONArray arr = (JSONArray) val;
                return Arrays.asList(arr.toArray(new String[0]));
            }
            list.add((String) val.toString());
            return list;
        } catch (com.jayway.jsonpath.InvalidPathException ex) {
            return new ArrayList<>();
        } catch (Exception ex) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                    + " Error: " + ex);
            return null;
        }
        
    }

}