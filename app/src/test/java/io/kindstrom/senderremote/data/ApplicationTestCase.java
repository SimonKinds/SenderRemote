package io.kindstrom.senderremote.data;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(application = ApplicationStub.class, sdk = 23, manifest = Config.NONE)
public abstract class ApplicationTestCase {
}
