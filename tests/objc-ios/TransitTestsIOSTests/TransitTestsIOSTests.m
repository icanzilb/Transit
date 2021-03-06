//
//  TransitTestsIOSTests.m
//  TransitTestsIOSTests
//
//  Created by Heiko Behrens on 08.02.13.
//  Copyright (c) 2013 BeamApp. All rights reserved.
//

#import <SenTestingKit/SenTestingKit.h>
#import "Transit.h"
#import "OCMock.h"

@interface TransitTestsIOSTests : SenTestCase
@end

@implementation TransitTestsIOSTests

-(void)testTransitNilSafeOnString {
    id value = @"undefined";
    id actual = TransitNilSafe(value);
    
    STAssertEqualObjects(value, actual, @"equal");
    STAssertTrue(value == actual, @"same");
    STAssertFalse([actual isJSExpression], @"plain string");
}

-(void)testTransitNilSafeOnNil {
    id value = nil;
    id actual = TransitNilSafe(value);
    
    STAssertFalse(value == actual, @"same");
    STAssertEqualObjects(@"undefined", actual, @"string containined 'undefined'");
    STAssertTrue([actual isJSExpression], @"marked as jsExpression");
}


@end
