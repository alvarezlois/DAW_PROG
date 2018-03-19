package TecnoWorkDriver;

class Manager {
	static TecnoWorkControlDriver initPresenceControl(TecnoWorkControlManager mgr) {
		return new PresenceControl(mgr);		
	}
}
