import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class BadRequestResponse implements Response {
  public final int CODE = 400;
  public final String REASON_PHRASE = "Bad Request";
  public final String CRLF = "\r\n";
  public final String server = "CSC667/Paul and Elaine's Server";

  private String responseMessage;
  byte[] body;

  public BadRequestResponse(Request request, Resource resource) {
    Date date = new Date();

    String statusLine = request.getHttpVersion() + " " + CODE + " " + REASON_PHRASE + CRLF;
    String dateHeader = "Date: " + date + CRLF;
    String serverHeader = "Server: " + server + CRLF;
    String contentTypeHeader = "Content-Type: text/html" + CRLF;

    String bodyString = "<html><body><h1 style=\"text-align:center\">" + 
      "400 Bad Request</h1></body></html>";
    body = bodyString.getBytes();

    String contentLengthHeader = "Content-Length: " + body.length + CRLF;

    responseMessage = responseMessage = statusLine + dateHeader + serverHeader + 
      contentLengthHeader + contentTypeHeader + CRLF;
  }

  public void send(DataOutputStream out) throws IOException {
    out.writeBytes(responseMessage);
    out.write(body, 0, body.length);
  }

  public String getResponseMessage() { return responseMessage; }
}