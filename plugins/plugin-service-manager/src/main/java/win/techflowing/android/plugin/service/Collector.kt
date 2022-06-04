package win.techflowing.android.plugin.service

import win.techflowing.service.annotation.Priority
import win.techflowing.service.annotation.Scope

/**
 * 数据搜集结果
 *
 * @author techflowing@gmail.com
 * @since 2022/6/5 12:19 上午
 */
object Collector {

    /** Service 映射关系 */
    private val serviceMap = mutableMapOf<String, MutableList<ServiceImplInfo>>()

    /**
     * 找到一个映射关系
     *
     * @param service 接口
     * @param serviceImpl 实现类
     * @param priority 实现类优先级
     */
    fun addService(service: String, serviceImpl: String, priority: Priority, scope: Scope) {
        val serviceImplList = serviceMap.getOrDefault(service, mutableListOf())
        serviceImplList.add(ServiceImplInfo(serviceImpl, priority, scope))
        serviceMap[service] = serviceImplList
    }

    /**
     * Service 实现类信息
     *
     * @property name 类名称
     * @property priority 优先级
     * @property scope 实现方式
     * @constructor Create empty Service impl info
     */
    data class ServiceImplInfo(val name: String, val priority: Priority, val scope: Scope)
}