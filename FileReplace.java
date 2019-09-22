import java.io.*;

public class FileReplace {
    public static void main(String args[]){
        String FilePath=args[0];
        String runFilePath=FilePath+"bin\\run.bat";
        String confXML_FilePath=FilePath+"conf\\web.xml";
        String webAppsXML_FilePath=FilePath+"webapps\\ROOT\\WEB-INF\\web.xml";
        replaceBatContent(runFilePath);
        System.out.println("File Changes done in : "+runFilePath);
        replaceConfXMLContent(confXML_FilePath);
        System.out.println("File Changes done in : "+confXML_FilePath);
        replaceWebAppsXMLContent(webAppsXML_FilePath);
        System.out.println("File Changes done in : "+webAppsXML_FilePath);
    }
    private static void replaceBatContent(String filePath){
        File fileToBeModified = new File(filePath);
        String content = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null)
            {
                content = content + line + System.lineSeparator();
                line = reader.readLine();
                if(line!=null && line.contains("set JAVA_OPTS=%JAVA_OPTS%  -Dtier-type=BE -Dtier-id=BE1 -Duser.region=US -Dstart.type=command")){
                    line="set JAVA_OPTS=%JAVA_OPTS%  -Dtier-type=BE -Dtier-id=BE1 -Duser.region=US -Dstart.type=command -Dorg.apache.jasper.compiler.Parser.STRICT_QUOTE_ESCAPING=false -Dorg.apache.jasper.compiler.Parser.STRICT_WHITESPACE=false";
                }
                if(line!=null && line.contains("rem set JAVA_OPTS=-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n %JAVA_OPTS%")){
                    line="set JAVA_OPTS=-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n %JAVA_OPTS%";
                }
            }
            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private static void replaceConfXMLContent(String filePath){
        File fileToBeModified = new File(filePath);
        String content = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            int flag=0;
            while (line != null)
            {
                content = content + line + System.lineSeparator();
                if(line.contains("<param-name>development</param-name>") || line.contains("<param-name>reloading</param-name>")){
                    flag=1;
                }
                line = reader.readLine();
                if(flag==1){
                    if( line!=null && line.contains("<param-value>false</param-value>")){
                        line="            <param-value>true</param-value>";
                    }
                    flag=0;
                }

                if(line!=null && !content.contains("<param-name>mappedfile</param-name>") &&line.contains("<load-on-startup>3</load-on-startup>")){
                    line="        <init-param>\n" +
                            "            <param-name>mappedfile</param-name>\n" +
                            "            <param-value>false</param-value>\n" +
                            "        </init-param>\n"+line;
                }
            }

            writer = new FileWriter(fileToBeModified);
            writer.write(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
    private static void replaceWebAppsXMLContent(String filePath){
        File fileToBeModified = new File(filePath);
        String content = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            int flag=0;
            while (line != null)
            {
                content = content + line + System.lineSeparator();
                if(line.contains("<param-name>development.mode</param-name>")){
                    flag=1;
                }
                line = reader.readLine();
                if(flag==1){
                    if( line!=null && line.contains("<param-value>false</param-value>")){
                        line="                  <param-value>true</param-value>";
                    }
                    flag=0;
                }
            }
            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
