package T145.magistics.api.logic;

import net.minecraft.block.properties.PropertyBool;

public interface IWorker {

	static final PropertyBool WORKING = PropertyBool.create("working");

	boolean isWorking();
}