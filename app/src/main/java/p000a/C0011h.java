package p000a;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: RootElement */
public class C0011h extends C0001c {
    final C0010a f19a;
    final Locator f20b;

    /* compiled from: RootElement */
    class C00091 implements Locator {
        final /* synthetic */ C0011h f13a;

        C00091(C0011h this$0) {
            this.f13a = this$0;
        }

        public String getPublicId() {
            Locator locator = this.f13a.f19a.f14a;
            return locator == null ? null : locator.getPublicId();
        }

        public String getSystemId() {
            Locator locator = this.f13a.f19a.f14a;
            return locator == null ? null : locator.getSystemId();
        }

        public int getLineNumber() {
            Locator locator = this.f13a.f19a.f14a;
            return locator == null ? -1 : locator.getLineNumber();
        }

        public int getColumnNumber() {
            Locator locator = this.f13a.f19a.f14a;
            return locator == null ? -1 : locator.getColumnNumber();
        }
    }

    /* compiled from: RootElement */
    class C0010a extends DefaultHandler {
        Locator f14a;
        int f15b = -1;
        C0001c f16c = null;
        StringBuilder f17d = null;
        final /* synthetic */ C0011h f18e;

        C0010a(C0011h this$0) {
            this.f18e = this$0;
        }

        public void setDocumentLocator(Locator locator) {
            this.f14a = locator;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            int depth = this.f15b + 1;
            this.f15b = depth;
            if (depth == 0) {
                m17a(uri, localName, attributes);
            } else if (this.f17d != null) {
                throw new C0000a("Encountered mixed content within text element named " + this.f16c + ".", this.f14a);
            } else if (depth == this.f16c.f2e + 1) {
                C0003b children = this.f16c.f4g;
                if (children != null) {
                    C0001c child = children.m13a(uri, localName);
                    if (child != null) {
                        m16a(child, attributes);
                    }
                }
            }
        }

        void m17a(String uri, String localName, Attributes attributes) throws SAXException {
            C0001c root = this.f18e;
            if (root.f0c.compareTo(uri) == 0 && root.f1d.compareTo(localName) == 0) {
                m16a(root, attributes);
                return;
            }
            throw new C0000a("Root element name does not match. Expected: " + root + ", Got: " + C0001c.m0c(uri, localName), this.f14a);
        }

        void m16a(C0001c e, Attributes attributes) {
            this.f16c = e;
            if (e.f7j != null) {
                e.f7j.start(attributes);
            }
            if (e.f9l != null) {
                this.f17d = new StringBuilder();
            }
            e.m3a();
            e.f6i = true;
        }

        public void characters(char[] buffer, int start, int length) throws SAXException {
            if (this.f17d != null) {
                this.f17d.append(buffer, start, length);
            }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            C0001c current = this.f16c;
            if (this.f15b == current.f2e) {
                current.m9a(this.f14a);
                if (current.f8k != null) {
                    current.f8k.end();
                }
                if (this.f17d != null) {
                    String body = this.f17d.toString();
                    this.f17d = null;
                    current.f9l.m14a(body);
                }
                this.f16c = current.f3f;
            }
            this.f15b--;
        }
    }

    public C0011h(String uri, String localName) {
        super(null, uri, localName, 0);
        this.f19a = new C0010a(this);
        this.f20b = new C00091(this);
    }

    public C0011h(String localName) {
        this("", localName);
    }

    public ContentHandler m18b() {
        return this.f19a;
    }

    public Locator m19c() {
        return this.f20b;
    }
}
