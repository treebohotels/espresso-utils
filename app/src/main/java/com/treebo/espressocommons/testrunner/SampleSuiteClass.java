package com.treebo.espressocommons.testrunner;

import com.treebo.espressocommons.configurations.BaseTest;
import com.treebo.espressocommons.testrunner.executiontypes.High;
import com.treebo.espressocommons.testrunner.executiontypes.Sanity;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by kishorepolisetty on 06/03/17.
 */

@RunWith(Categories.class)
@Categories.IncludeCategory({Sanity.class})
@Categories.ExcludeCategory({High.class})
@Suite.SuiteClasses(
        {
                // All Classes
        })

public class SampleSuiteClass extends BaseTest{

}
