'use client';
import React, {FunctionComponent, PropsWithChildren} from 'react';
import {useIsAuthenticated} from '@/hooks';
import PrivateLayout from './PrivateLayout';
import PublicLayout from './PublicLayout';
import Script from "next/script";

/**
 * Returns the current Layout component depending on different circumstances.
 * @layout CurrentLayout
 */
const CurrentLayout: FunctionComponent<PropsWithChildren> = (props) => {


    return useIsAuthenticated() ? <PrivateLayout {...props} /> : <PublicLayout {...props}>

    </PublicLayout>;
};

export default CurrentLayout;
