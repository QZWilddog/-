/**
 * CurrentHolder 类用于在当前线程中存储和获取员工 ID。
 * 该类使用 ThreadLocal 来确保每个线程都有自己独立的员工 ID 副本。
 */
package cn.zimeedu.utils;
// 一次请求对应一次请求
// 将当前员工信息从令牌中 就是需要记录当前操作网页的人  将当前员工信息从令牌中解析出来的信息传递给AOP、Controller或Service程序使用  用来记录操作日志LogControoler
public class CurrentHolder {

    /**  为每个线程提供一份单独的存储空间，具有线程隔离的效果，不同线程之间不会互相干扰
     * 静态常量 CURRENT_LOCAL，用于存储当前线程的员工 ID。
     * ThreadLocal 确保每个线程都有自己独立的 Integer 类型的员工 ID 副本。
     */
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();  //ThreadClocal：不是一个Thread（线程） 而是Thread的局部变量  里面就这三个方法

    /**
     * 设置当前线程的员工 ID。
     *
     * @param employeeId 要设置的员工 ID。
     */
    public static void setCurrentId(Integer employeeId){
        // 将传入的员工 ID 存储到当前线程的 ThreadLocal 中
        CURRENT_LOCAL.set(employeeId);
    }

    /**
     * 获取当前线程的员工 ID。
     *
     * @return 当前线程的员工 ID，如果未设置则返回 null。
     */
    public static Integer getCurrentId(){
        // 从当前线程的 ThreadLocal 中获取存储的员工 ID
        return CURRENT_LOCAL.get();
    }

    /**
     * 移除当前线程中存储的员工 ID。
     * 建议在使用完 ThreadLocal 后调用此方法，以避免内存泄漏。
     */
    public static void remove(){
        // 移除当前线程的 ThreadLocal 中存储的员工 ID
        CURRENT_LOCAL.remove();
    }
}
