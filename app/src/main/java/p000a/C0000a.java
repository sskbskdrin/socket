package p000a;

import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

/* compiled from: BadXmlException */
class C0000a extends SAXParseException {
    public C0000a(String message, Locator locator) {
        super(message, locator);
    }

    public String getMessage() {
        return "Line " + getLineNumber() + ": " + super.getMessage();
    }
}
