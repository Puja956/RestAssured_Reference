import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;

public class Soap_Api_Reference {

	public static void main(String[] args) {
		
			//declare the base URL
					RestAssured.baseURI = "https://www.dataaccess.com/";
					// Declare response body
					String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
							+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + "  <soap:Body>\r\n"
							+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
							+ "      <ubiNum>100</ubiNum>\r\n" + "    </NumberToWords>\r\n" + "  </soap:Body>\r\n" + "</soap:Envelope>";
				
					//Extract ResponseBody
					 String ResponseBody=given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).
							 when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
					 System.out.println(ResponseBody);
					 //Parse the ResponseBody
					 XmlPath XmlResponse = new XmlPath(ResponseBody);
					 String ResponseParameter = XmlResponse.getString("NumberToWordsResult");
				     System.out.println(ResponseParameter);
				     //Validate the ResponseBody
				     Assert.assertEquals(ResponseParameter, "one hundred ");
				     }

				}