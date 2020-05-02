/**
 * 
 */
package genericCheckpointing.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import genericCheckpointing.server.StoreRestoreI;

/**
 * <p>
 * This class contains the logic to create an instance of proxy.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public class ProxyCreator {

	/**
	 * <p>
	 * This class creates an instance of a proxy.
	 * </p>
	 * 
	 * @param interfaceArray
	 *            array of interface whose methods are to be intercepted by
	 *            invocation handler.
	 * @param handler
	 *            an instance of handler which intercepts calls to methods
	 *            defined in the array of interfaces.
	 * @return a reference to the proxy instance.
	 */
	public StoreRestoreI createProxy(Class<?>[] interfaceArray, InvocationHandler handler) {
		StoreRestoreI storeRestoreRef = (StoreRestoreI) Proxy.newProxyInstance(getClass().getClassLoader(),
				interfaceArray, handler);

		return storeRestoreRef;
	}
}
