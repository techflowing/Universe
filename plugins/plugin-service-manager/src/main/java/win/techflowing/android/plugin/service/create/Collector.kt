package win.techflowing.android.plugin.service.create

import win.techflowing.android.plugin.common.log.ILogger
import win.techflowing.service.manager.annotation.Priority
import win.techflowing.service.manager.annotation.Scope
import java.io.File
import java.io.Serializable

/**
 * 数据搜集器，处理优先级对比等逻辑，缓存一下搜集的数据
 *
 * @author techflowing@gmail.com
 * @since 2022/6/5 12:19 上午
 */
class Collector : Serializable {

    companion object {
        const val TAG = "Collector"
    }

    /** Service 映射关系，只保留当前最大的优先级实现类 */
    private val serviceMap = mutableMapOf<String, ServiceImplInfo>()

    /**
     * 通过注解扫描到一个 ServiceImpl 类
     *
     * @param serviceImpl ServiceImpl 类
     * @param serviceList 实现的接口列表，有可能是多实现
     * @param priority 优先级
     * @param scope Scope
     */
    fun onScanServiceImpl(
        serviceImpl: String,
        serviceList: List<String>,
        priority: Priority,
        scope: Scope,
        output: File,
        logger: ILogger
    ) {
        serviceList.forEach { service ->
            onFindOneServiceMap(service, serviceImpl, priority, scope, output, logger)
        }
    }

    /**
     * 找到一个映射关系
     *
     * @param service 接口
     * @param serviceImpl 实现类
     * @param priority 实现类优先级
     */
    private fun onFindOneServiceMap(
        service: String,
        serviceImpl: String,
        priority: Priority,
        scope: Scope,
        output: File,
        logger: ILogger
    ) {
        logger.i(
            TAG, "找到接口类：$service，对应的实现类：$serviceImpl，" +
                    "实现类优先级：${priority} - ${priority.value}，实现类Scope：$scope"
        )
        // 不存在则直接创建新的
        if (!serviceMap.containsKey(service)) {
            logger.i(TAG, "为 $service 创建 provider 类")
            ServiceProviderCreator().createServiceProvider(service, serviceImpl, scope, output)
            serviceMap[service] = ServiceImplInfo(serviceImpl, priority, scope)
            return
        }
        // 存在则需要判断对比优先级
        serviceMap[service]?.also {
            if (priority > it.priority) {
                logger.i(TAG, "$serviceImpl 的优先级比 ${it.name} 高，为 $service 创建新的 provider 类覆盖旧的")
                ServiceProviderCreator().createServiceProvider(service, serviceImpl, scope, output)
                serviceMap[service] = ServiceImplInfo(serviceImpl, priority, scope)
            }
        }
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