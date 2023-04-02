package shakh.billingsystem.utilities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.Company;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class ThreadLocalSingleton {

    private static final ThreadLocal<Admins> ADMIN= ThreadLocal.withInitial(Admins::new);
    private static final ThreadLocal<String> LOG_INTERNAL_ID = ThreadLocal.withInitial(String::new);
    private static final ThreadLocal<String> EXTERNAL_IP = ThreadLocal.withInitial(String::new);
    private static final ThreadLocal<Company> COMPANY = ThreadLocal.withInitial(Company::new);

    public static void remove() {
        ThreadLocalSingleton.ADMIN.remove();
        ThreadLocalSingleton.LOG_INTERNAL_ID.remove();
        ThreadLocalSingleton.EXTERNAL_IP.remove();
        ThreadLocalSingleton.COMPANY.remove();
    }


    public static Admins getAdmin() {
        return ADMIN.get();
    }

    public static void setAdmin(Admins admin) {
        ThreadLocalSingleton.ADMIN.set(admin);
    }

    public static Company getCompany() {
        return COMPANY.get();
    }

    public static void setCompany(Company company) {
        ThreadLocalSingleton.COMPANY.set(company);
    }





    public static String getLogInternalId() {
        return ThreadLocalSingleton.LOG_INTERNAL_ID.get();
    }

    public static void setLogInternalId(String internalId) {
        ThreadLocalSingleton.LOG_INTERNAL_ID.set(internalId);
    }

    public static String getExternalIp() {
        return ThreadLocalSingleton.EXTERNAL_IP.get();
    }

    public static void setExternalIp(String internalId) {
        ThreadLocalSingleton.EXTERNAL_IP.set(internalId);
    }

}