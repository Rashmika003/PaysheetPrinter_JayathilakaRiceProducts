package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.pdfPrinting;

import javax.usb.*;
import java.util.List;

public class USBPrinterUtil {
    private static final short VENDOR_ID = (short) 0x04b8;  // Epson
    private static final short PRODUCT_ID = (short) 0x0202;  // TM-T81III (you may need to adjust this)

    public static UsbDevice findPrinter() throws UsbException {
        UsbServices services = UsbHostManager.getUsbServices();
        UsbHub rootHub = services.getRootUsbHub();
        return findDevice(rootHub);
    }

    private static UsbDevice findDevice(UsbHub hub) {
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == VENDOR_ID && desc.idProduct() == PRODUCT_ID) {
                return device;
            }
            if (device.isUsbHub()) {
                device = findDevice((UsbHub) device);
                if (device != null) {
                    return device;
                }
            }
        }
        return null;
    }

    public static void sendData(UsbDevice device, byte[] data) throws UsbException {
        UsbConfiguration configuration = device.getActiveUsbConfiguration();
        UsbInterface iface = configuration.getUsbInterface((byte) 0);
        iface.claim(usbInterface -> true);

        try {
            UsbEndpoint endpoint = iface.getUsbEndpoint((byte) 1);
            UsbPipe pipe = endpoint.getUsbPipe();
            pipe.open();
            try {
                int sent = pipe.syncSubmit(data);
                System.out.println("Sent " + sent + " bytes");
            } finally {
                pipe.close();
            }
        } finally {
            iface.release();
        }
    }
}
