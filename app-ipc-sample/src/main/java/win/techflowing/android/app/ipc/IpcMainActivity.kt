package win.techflowing.android.app.ipc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import win.techflowing.android.app.ipc.apple.*
import win.techflowing.android.app.ipc.banana.BananaProcessActivity
import win.techflowing.android.app.ipc.banana.BananaService
import win.techflowing.android.base.BaseActivity
import win.techflowing.android.ipc.Tartarus
import win.techflowing.android.log.XLog

/**
 * 首页
 *
 * @author techflowing@gmail.com @version 1.0.0
 * @since 2022/5/6 11:24 下午
 */
class IpcMainActivity : BaseActivity(), View.OnClickListener {

    val TAG = "IpcMainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_main)

        findViewById<Button>(R.id.start_apple_process_activity).setOnClickListener {
            startActivity(Intent(this, AppleProcessActivity::class.java))
        }

        findViewById<Button>(R.id.start_banana_process_activity).setOnClickListener {
            startActivity(Intent(this, BananaProcessActivity::class.java))
        }

        findViewById<Button>(R.id.get_apple_process_service).setOnClickListener {
            Tartarus.getRemoteService(AppleService::class.java)?.also {
                Toast.makeText(this@IpcMainActivity, "苹果进程名称：" + it.getAppleName(), Toast.LENGTH_LONG).show()
            }
        }

        findViewById<Button>(R.id.get_banana_process_service).setOnClickListener {
            Tartarus.getRemoteService(BananaService::class.java)?.also {
                Toast.makeText(this@IpcMainActivity, "香蕉进程名称：" + it.getBananaName(), Toast.LENGTH_LONG).show()
                XLog.e(TAG, it.hashCode())
            }
        }

        findViewById<Button>(R.id.test_basic_parameter).setOnClickListener(this)
        findViewById<Button>(R.id.test_basic_array_parameter).setOnClickListener(this)
        findViewById<Button>(R.id.test_box_array_parameter).setOnClickListener(this)
        findViewById<Button>(R.id.test_string_parameter).setOnClickListener(this)
        findViewById<Button>(R.id.test_parcelable_parameter).setOnClickListener(this)
        findViewById<Button>(R.id.test_collect_parameter).setOnClickListener(this)
        findViewById<Button>(R.id.test_out_annotation_parameter).setOnClickListener(this)
        findViewById<Button>(R.id.test_callback_parameter).setOnClickListener(this)
        findViewById<Button>(R.id.test_parameter_null).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val service = Tartarus.getRemoteService(ParameterService::class.java) ?: return
        when (v?.id) {
            R.id.test_basic_parameter -> {
                basicTypeParamTransfer(service)
            }
            R.id.test_basic_array_parameter -> {
                basicArrayTypeParamTransfer(service)
            }
            R.id.test_box_array_parameter -> {
                boxArrayTypeParamTransfer(service)
            }
            R.id.test_string_parameter -> {
                stringTypeParamTransfer(service)
            }
            R.id.test_parcelable_parameter -> {
                parcelableTypeParamTransfer(service)
            }
            R.id.test_collect_parameter -> {
                collectionTypeParamTransfer(service)
            }
            R.id.test_out_annotation_parameter -> {
                outAnnotationTypeParamTransfer(service)
            }
            R.id.test_parameter_null -> {
                nullTypeParamTransfer(service)
            }
        }
    }

    /**
     * 空类型参数
     */
    private fun nullTypeParamTransfer(service: ParameterService) {
        Toast.makeText(this@IpcMainActivity, "不支持空类型参数", Toast.LENGTH_LONG).show()
        // service.nullParameter(null, null, null, null, null)
    }

    /**
     * 测试基础类型的参数传递
     */
    private fun basicTypeParamTransfer(service: ParameterService) {
        service.basicType(1, 100, 1000, 10000, 1f, 1.0, true, 'A')
    }

    /**
     * 测试基础数组类型的参数传递
     */
    private fun basicArrayTypeParamTransfer(service: ParameterService) {
        service.basicArrayType(
            ByteArray(3) { it.toByte() },
            ShortArray(3) { it.toShort() },
            IntArray(5) { it },
            LongArray(5) { (it * 10).toLong() },
            FloatArray(3) { it * 1f },
            DoubleArray(5) { it * 1.0 },
            BooleanArray(5) { it % 2 == 0 },
            CharArray(3) { 'A' + it }
        )
    }

    /**
     * 测试包装数组类型的参数传递
     */
    private fun boxArrayTypeParamTransfer(service: ParameterService) {
        val byteArray = Array<Byte?>(5) { it.toByte() }
        val shortArray = Array<Short?>(6) { it.toShort() }
        val intArray = Array<Int?>(7) { it }
        val longArray = Array<Long?>(8) { (it * 10).toLong() }
        val floatArray = Array<Float?>(9) { it * 1f }
        val doubleArray = Array<Double?>(10) { it * 1.0 }
        val booleanArray = Array<Boolean?>(11) { it % 2 == 0 }
        val charArray = Array<Char?>(12) { 'A' + it }

        service.boxArrayType(
            byteArray, shortArray, intArray, longArray, floatArray, doubleArray, booleanArray, charArray
        )

        XLog.d(TAG, "远程方法调用完成后，数据处理后的结果")
        XLog.d(TAG, "Byte 类型数组：${byteArray.contentToString()}")
        XLog.d(TAG, "Short 类型数组：${shortArray.contentToString()}")
        XLog.d(TAG, "Int 类型数组：${intArray.contentToString()}")
        XLog.d(TAG, "Long 类型数组：${longArray.contentToString()}")
        XLog.d(TAG, "Float 类型数组：${floatArray.contentToString()}")
        XLog.d(TAG, "Double 类型数组：${doubleArray.contentToString()}")
        XLog.d(TAG, "Boolean 类型数组：${booleanArray.contentToString()}")
        XLog.d(TAG, "Char 类型数组：${charArray.contentToString()}")
    }

    /**
     * 测试包装数组类型的参数传递
     */
    private fun stringTypeParamTransfer(service: ParameterService) {
        val stringArray = Array<String?>(5) { "这是 String -> $it" }
        val charSequenceArray = Array<CharSequence?>(5) { "这是 CharSequence -> $it" }

        service.stringType("测试 String", stringArray, "测试 CharSequence", charSequenceArray)

        XLog.d(TAG, "远程方法调用完成后，数据处理后的结果")
        XLog.d(TAG, "String 类型数组：${stringArray.contentToString()}")
        XLog.d(TAG, "CharSequence 类型数组：${charSequenceArray.contentToString()}")
    }

    /**
     * Parcelable 类型参数传递
     */
    private fun parcelableTypeParamTransfer(service: ParameterService) {
        val model = ParcelableModel("Jerry", 18)
        val modelArray = arrayOf(ParcelableModel("Tom", 28), ParcelableModel("Mark", 30))
        service.parcelableType(model, modelArray)
        XLog.d(TAG, "远程方法调用完成后，数据处理后的结果")
        XLog.d(TAG, model.toString())
        XLog.d(TAG, modelArray.contentToString())
    }

    /**
     * 集合类参数类型传递
     */
    private fun collectionTypeParamTransfer(service: ParameterService) {
        val list = mutableListOf("1", "2", "3")
        val map = mutableMapOf(
            "name" to "Tom",
            "age" to "18"
        )
        service.collectType(list, map)
        XLog.d(TAG, "远程方法调用完成后，数据处理后的结果")
        XLog.d(TAG, list)
        XLog.d(TAG, map)
    }

    /**
     * @Out 注解参数类型
     */
    private fun outAnnotationTypeParamTransfer(service: ParameterService) {
        val intArray = Array<Int?>(7) { it }
        val model = ParcelableModel("Jerry", 18)
        val list = mutableListOf("1", "2", "3")

        service.outParameter(intArray, model, list)

        XLog.d(TAG, "远程方法调用完成后，数据处理后的结果")
        XLog.d(ParameterServiceImpl.TAG, "数据：${intArray.contentToString()}")
        XLog.d(ParameterServiceImpl.TAG, "数据：$model")
        XLog.d(ParameterServiceImpl.TAG, "数据：$list")
    }
}