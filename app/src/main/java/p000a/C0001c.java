package p000a;

import java.util.ArrayList;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

/* compiled from: Element */
public class C0001c {
    final String f0c;
    final String f1d;
    final int f2e;
    final C0001c f3f;
    C0003b f4g;
    ArrayList<C0001c> f5h;
    boolean f6i;
    C0005i f7j;
    C0004e f8k;
    C0007f f9l;

    C0001c(C0001c parent, String uri, String localName, int depth) {
        this.f3f = parent;
        this.f0c = uri;
        this.f1d = localName;
        this.f2e = depth;
    }

    public C0001c m1a(String localName) {
        return m2a("", localName);
    }

    public C0001c m2a(String uri, String localName) {
        if (this.f9l != null) {
            throw new IllegalStateException("This element already has an end text element listener. It cannot have children.");
        }
        if (this.f4g == null) {
            this.f4g = new C0003b();
        }
        return this.f4g.m12a(this, uri, localName);
    }

    public C0001c m10b(String localName) {
        return m11b("", localName);
    }

    public C0001c m11b(String uri, String localName) {
        C0001c child = m2a(uri, localName);
        if (this.f5h == null) {
            this.f5h = new ArrayList();
            this.f5h.add(child);
        } else if (!this.f5h.contains(child)) {
            this.f5h.add(child);
        }
        return child;
    }

    public void m4a(C0006d elementListener) {
        m7a((C0005i) elementListener);
        m5a((C0004e) elementListener);
    }

    public void m8a(C0012j elementListener) {
        m7a((C0005i) elementListener);
        m6a((C0007f) elementListener);
    }

    public void m7a(C0005i startElementListener) {
        if (this.f7j != null) {
            throw new IllegalStateException("Start element listener has already been set.");
        }
        this.f7j = startElementListener;
    }

    public void m5a(C0004e endElementListener) {
        if (this.f8k != null) {
            throw new IllegalStateException("End element listener has already been set.");
        }
        this.f8k = endElementListener;
    }

    public void m6a(C0007f endTextElementListener) {
        if (this.f9l != null) {
            throw new IllegalStateException("End text element listener has already been set.");
        } else if (this.f4g != null) {
            throw new IllegalStateException("This element already has children. It cannot have an end text element listener.");
        } else {
            this.f9l = endTextElementListener;
        }
    }

    public String toString() {
        return C0001c.m0c(this.f0c, this.f1d);
    }

    static String m0c(String uri, String localName) {
        StringBuilder append = new StringBuilder().append("'");
        if (!uri.equals("")) {
            localName = uri + ":" + localName;
        }
        return append.append(localName).append("'").toString();
    }

    void m3a() {
        ArrayList<C0001c> requiredChildren = this.f5h;
        if (requiredChildren != null) {
            for (int i = requiredChildren.size() - 1; i >= 0; i--) {
                ((C0001c) requiredChildren.get(i)).f6i = false;
            }
        }
    }

    void m9a(Locator locator) throws SAXParseException {
        ArrayList<C0001c> requiredChildren = this.f5h;
        if (requiredChildren != null) {
            int i = requiredChildren.size() - 1;
            while (i >= 0) {
                C0001c child = (C0001c) requiredChildren.get(i);
                if (child.f6i) {
                    i--;
                } else {
                    throw new C0000a("Element named " + this + " is missing required" + " child element named " + child + ".", locator);
                }
            }
        }
    }
}
