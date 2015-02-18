package cn.com.alcatel_sbell.utils;

import java.io.IOException;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

public class LdapUtils {
	public static boolean ldapcaauthorization(String cslname, String password)
			throws Exception {
		LdapConnection connection = new LdapNetworkConnection(
				"ldapca.na.alcatel.com", 389);

		EntryCursor cursor = connection.search("o=Alcatel", "uid=" + cslname,
				SearchScope.ONELEVEL);
		while (cursor.next()) {
			Entry entry = cursor.get();
			String dn = entry.get("dn").getString();
			try {
				connection.bind(dn, password);
				connection.close();
				return true;
			} catch (Exception e) {
				System.out.println("ldapca anthorization error!");
				connection.close();
				return false;
			} finally {
				cursor.close();
				connection.close();
			}
		}
		connection.close();
		return false;
	}

	public static Boolean cnauthorization(String cslname, String password)
			throws IOException {
		// todo:此处只实现了ad4域的用户验证
		/**
		 * $ds = ldap_connect('ldapca.na.alcatel.com');
		 * 
		 * @ldap_bind($ds); $search = ldap_search($ds, "o=Alcatel",
		 *                  "uid=".$csl); if( ldap_count_entries($ds,$search) ==
		 *                  1 ) { $info = ldap_get_entries($ds, $search); $bind
		 *                  = @ldap_bind($ds, $info[0]['dn'], $password); if(
		 *                  !$bind || !isset($bind)) return false; else return
		 *                  true; ldap_unbind($ds); } else return false;
		 */
		LdapConnection connection = new LdapNetworkConnection(
				"ad4.ad.alcatel.com", 389);
		String dn = "cn=" + cslname
				+ ",ou=users,ou=cnasb,ou=cn,dc=ad4,dc=ad,dc=alcatel,dc=com";

		try {
			connection.bind(dn, password);
			return true;
		} catch (Exception e) {
			System.out.println("ad4 anthorization error!");
			return false;
		} finally {
			connection.close();
		}

	}
}