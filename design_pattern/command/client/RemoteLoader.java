package algorithm.design_pattern.command.client;

import algorithm.design_pattern.command.commander.LightOffCommand;
import algorithm.design_pattern.command.commander.LightOnCommand;
import algorithm.design_pattern.command.commander.StereoOffCommand;
import algorithm.design_pattern.command.commander.StereoOnCommand;
import algorithm.design_pattern.command.commander.WindowCloseCommand;
import algorithm.design_pattern.command.commander.WindowOpenCommand;
import algorithm.design_pattern.command.invoker.RemoteControl;
import algorithm.design_pattern.command.receiver.Light;
import algorithm.design_pattern.command.receiver.Stereo;
import algorithm.design_pattern.command.receiver.Window;

public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("거실 등");
        Light kitchenLight = new Light("부엌 등");
        Window livingRoomWindow = new Window("거실 창문");
        Window kitchenWindow = new Window("부엌 창문");
        Stereo livingRoomStereo = new Stereo("거실 스테레오", "클락션");

        // 조명용 커맨드 객체
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        // 창문용 커맨드 객체
        WindowOpenCommand livingRoomWindowOpen = new WindowOpenCommand(livingRoomWindow);
        WindowCloseCommand livingRoomWindowClose = new WindowCloseCommand(livingRoomWindow);
        WindowOpenCommand kitchenRoomWindowOpen = new WindowOpenCommand(kitchenWindow);
        WindowCloseCommand kitchenWindowClose = new WindowCloseCommand(kitchenWindow);

        // 스테레오용 커맨드 객체
        StereoOnCommand livingRoomStereoOn = new StereoOnCommand(livingRoomStereo);
        StereoOffCommand livingRoomStereoOff = new StereoOffCommand(livingRoomStereo);

        // 리모컨 슬롯 세팅
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, livingRoomWindowOpen, livingRoomWindowClose);
        remoteControl.setCommand(3, kitchenRoomWindowOpen, kitchenWindowClose);
        remoteControl.setCommand(4, livingRoomStereoOn, livingRoomStereoOff);

        System.out.println(remoteControl);

        // client
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);
        remoteControl.onButtonWasPushed(4);
        remoteControl.offButtonWasPushed(4);
        // 설정 안된 슬롯은 NoCommand 객체로 초기화되어 있으므로, 아무 일도 일어나지 않고 문제가 발생하지도 않는다.
        remoteControl.onButtonWasPushed(5);
        remoteControl.offButtonWasPushed(5);
    }

}
