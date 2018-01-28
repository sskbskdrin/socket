package p000a;

/* compiled from: Children */
class C0003b {
    C0002a[] f12a = new C0002a[16];

    /* compiled from: Children */
    static class C0002a extends C0001c {
        final int f10a;
        C0002a f11b;

        C0002a(C0001c parent, String uri, String localName, int depth, int hash) {
            super(parent, uri, localName, depth);
            this.f10a = hash;
        }
    }

    C0003b() {
    }

    C0001c m12a(C0001c parent, String uri, String localName) {
        int hash = (uri.hashCode() * 31) + localName.hashCode();
        int index = hash & 15;
        C0002a current = this.f12a[index];
        if (current == null) {
            current = new C0002a(parent, uri, localName, parent.f2e + 1, hash);
            this.f12a[index] = current;
            return current;
        }
        C0002a previous;
        do {
            if (current.f10a == hash && current.f0c.compareTo(uri) == 0 && current.f1d.compareTo(localName) == 0) {
                return current;
            }
            previous = current;
            current = current.f11b;
        } while (current != null);
        current = new C0002a(parent, uri, localName, parent.f2e + 1, hash);
        previous.f11b = current;
        return current;
    }

    C0001c m13a(String uri, String localName) {
        int hash = (uri.hashCode() * 31) + localName.hashCode();
        C0002a current = this.f12a[hash & 15];
        if (current == null) {
            return null;
        }
        do {
            if (current.f10a == hash && current.f0c.compareTo(uri) == 0 && current.f1d.compareTo(localName) == 0) {
                return current;
            }
            current = current.f11b;
        } while (current != null);
        return null;
    }
}
